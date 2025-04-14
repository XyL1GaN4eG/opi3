package space.nerfthis.data;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * JSF Validator for ensuring R parameter values comply with application
 * constraints. <p> Validates that the R (radius) value falls within the
 * acceptable range of 1 to 4 (inclusive). Throws validation errors with
 * user-friendly messages when constraints are violated.
 * </p>
 *
 * <p>Registered in JSF via {@code @FacesValidator("rValidator")} for use in
 * view components.</p>
 */
@FacesValidator("rValidator")
public class RValidator implements Validator<Double> {

  /**
   * Validates whether the R value meets the 1-4 range requirement.
   *
   * @param context JSF faces context
   * @param component UI component being validated
   * @param value R value to validate
   * @throws ValidatorException if validation fails, containing:
   *         <ul>
   *           <li>Error summary: "Invalid R value"</li>
   *           <li>Error detail: "R must be between 1 and 4."</li>
   *         </ul>
   */
  @Override
  public void validate(FacesContext context, UIComponent component,
                       Double value) throws ValidatorException {
    if (value == null || value < 1 || value > 4) {
      throw new ValidatorException(
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid R value",
                           "R must be between 1 and 4."));
    }
  }
}
