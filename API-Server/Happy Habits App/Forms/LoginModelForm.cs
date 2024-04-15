using System.Xml.Linq;

namespace Happy_Habits_App.Forms
{
    public class LoginModelForm
    {
        public string? Email { get; set; }
        public string? Password { get; set; }

        public bool IsValid
        {
            get
            {
            return !string.IsNullOrEmpty(Email) &&
                   !string.IsNullOrEmpty(Password);
            }
        }
    }
}
