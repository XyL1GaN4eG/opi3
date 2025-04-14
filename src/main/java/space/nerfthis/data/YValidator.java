package space.nerfthis.data;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * JSF Validator for ensuring Y coordinate values comply with application
 * constraints. <p> Validates that the Y coordinate falls within the acceptable
 * range of -5 to 3 (inclusive). Throws validation errors with user-friendly
 * messages when constraints are violated.
 * </p>
 *
 * <p>Registered in JSF via {@code @FacesValidator("yValidator")} for use in
 * view components.</p>
 */
@FacesValidator("yValidator")
public class YValidator implements Validator<Double> {

  /**
   * Validates whether the Y value meets the -5 to 3 range requirement.
   *
   * @param context JSF faces context
   * @param component UI component being validated
   * @param value Y value to validate
   * @throws ValidatorException if validation fails, containing:
   *         <ul>
   *           <li>Error summary: "Invalid Y value"</li>
   *           <li>Error detail: "Y must be between -5 and 3."</li>
   *         </ul>
   */
  @Override
  public void validate(FacesContext context, UIComponent component,
                       Double value) throws ValidatorException {
    if (value == null || value < -5 || value > 3) {
      throw new ValidatorException(
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Y value",
                           "Y must be between -5 and 3."));
    }
  }
}
