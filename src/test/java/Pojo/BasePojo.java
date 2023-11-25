package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BasePojo {
	
	@JsonIgnore
	private String ExpectedCode;

	@JsonIgnore
	private String TestCaseID;
	
	@JsonIgnore
	private String ExpectedErrorMessage;
	
	@JsonIgnore
	private String TestScenarioDescription;
	
}
