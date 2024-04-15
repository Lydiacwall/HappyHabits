namespace Happy_Habits_App.Forms
{
    public class SignUpModelForm
    {
        public string? Username { get; set; }
        public string? Password { get; set; }
        public string? Email { get; set; }

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(Username) &&
                       !string.IsNullOrEmpty(Email) &&
                       !string.IsNullOrEmpty(Password);
            }
        }
    }
}
