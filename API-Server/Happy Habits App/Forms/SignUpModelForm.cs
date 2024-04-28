using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class SignUpModelForm
    {
        [JsonPropertyName("firstName")]
        public string? FirstName { get; set; }
        [JsonPropertyName("lastName")]
        public string? LastName { get; set; }
        [JsonPropertyName("Password")]
        public string? Password { get; set; }
        [JsonPropertyName("email")]
        public string? Email { get; set; }
        [JsonPropertyName("birthdate")]
        public string? Birthdate {  get; set; }
        [JsonPropertyName("speciality")]
        public string? Speciality { get; set; }

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(FirstName) &&
                       !string.IsNullOrEmpty(Email) &&
                       !string.IsNullOrEmpty(Password);
            }
        }
    }
}
