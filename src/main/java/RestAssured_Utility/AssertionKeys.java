package RestAssured_Utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssertionKeys {

	@NonNull private String jsonPath;
	@NonNull private Object expectedValue;
	@NonNull private Object actualValue;
	@NonNull private Object result;


}
