using System.ComponentModel.DataAnnotations;

namespace Admin.ViewModels.AccountViewModels
{
    public class ForgotPasswordViewModel
    {
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        [EmailAddress(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "WrongEmail")]
        public string Email { get; set; }
    }
}
