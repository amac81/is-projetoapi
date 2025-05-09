package pt.arnaldocanelas.projetoapi.dto;

import java.time.LocalDateTime;

public class CustomErrorDTO {
	private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
    
    public CustomErrorDTO(Integer status, String error, String path) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getPath() {
		return path;
	}
           
}