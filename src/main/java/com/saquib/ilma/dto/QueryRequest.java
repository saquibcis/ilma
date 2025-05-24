package com.saquib.ilma.dto;

public class QueryRequest {

	private String query;
    private Integer topK;
    private Double similarityThreshold;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Integer getTopK() {
		return topK;
	}
	public void setTopK(Integer topK) {
		this.topK = topK;
	}
	public Double getSimilarityThreshold() {
		return similarityThreshold;
	}
	public void setSimilarityThreshold(Double similarityThreshold) {
		this.similarityThreshold = similarityThreshold;
	}
	
	
    
    
}
