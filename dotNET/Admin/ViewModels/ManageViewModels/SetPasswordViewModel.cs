using System.ComponentModel.DataAnnotations;

namespace Admin.ViewModels.ManageViewModels
{
    public class SetPasswordViewModel
    {
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        [StringLength(100, ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "PasswordLength")]
        [DataType(DataType.Password)]
        public string NewPassword { get; set; }

        [DataType(DataType.Password)]
        [Compare("NewPassword", ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "PasswordCompare")]
        public string ConfirmPassword { get; set; }
    }
}
