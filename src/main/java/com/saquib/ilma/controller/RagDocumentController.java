package com.saquib.ilma.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.pinecone.PineconeVectorStore;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RagDocumentController {

	
	private final PineconeVectorStore vectorStore;

	RagDocumentController(PineconeVectorStore vectorStore){
		this.vectorStore = vectorStore;	
	}

	@PostMapping("/rag/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

		String filename = file.getOriginalFilename();
		String text;

		if (filename == null) {
			return ResponseEntity.badRequest().body("Invalid file");
		}

		try {
			String ext = StringUtils.getFilenameExtension(filename).toLowerCase();
			if ("pdf".equals(ext)) {
				try (PDDocument pdf = PDDocument.load(file.getInputStream())) {
					text = new PDFTextStripper().getText(pdf);
				}
			} else if ("docx".equals(ext) || "doc".equals(ext)) {
				try (XWPFDocument docx = new XWPFDocument(file.getInputStream());
						XWPFWordExtractor extractor = new XWPFWordExtractor(docx)) {
					text = extractor.getText();
				}
			} else if ("txt".equals(ext)) {
				text = new String(file.getBytes(), StandardCharsets.UTF_8);
			} else {
				return ResponseEntity.badRequest().body("Unsupported file type");
			}
		} catch (IOException e) {
			return ResponseEntity.status(500).body("Error parsing file: " + e.getMessage());
		}

		/*String content = new String(file.getBytes());
		Document document = new Document(content); */
		//using textsplitter for better results
		TextSplitter splitter = new TokenTextSplitter(
				512,               // chunkSize (tokens)
				100,               // minChunkSizeChars
				50,                // minChunkLengthToEmbed
				Integer.MAX_VALUE, // maxNumChunks
				true               // keepSeparator
				);

		// Prepare metadata
		System.out.println("Creating Metadata");
		Map<String, Object> metadata = new HashMap<>();
		metadata.put("source", filename);
		System.out.println("Splitting the doc in smaller chunks");
		List<Document> splitDocs = splitter.split(List.of(new Document(text,metadata)));
		System.out.println("uploading the document in vector store");
		vectorStore.add(splitDocs);

		return ResponseEntity.ok("File '" + filename + "' uploaded and processed");

	}
}
