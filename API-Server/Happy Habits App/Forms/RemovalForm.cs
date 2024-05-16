using System.Text.Json.Serialization;
using System.Xml.Linq;

namespace Happy_Habits_App.Forms
{
    public class RemovalForm
    {
        [JsonPropertyName("userId")]
        public string? UserId { get; set; }
        [JsonPropertyName("Id")]
        public string? Id { get; set; }

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !string.IsNullOrEmpty(Id);
            }
        }
    }
}
