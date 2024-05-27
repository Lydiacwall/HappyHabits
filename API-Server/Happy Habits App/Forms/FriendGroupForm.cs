using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class FriendGroupForm
    {
        [JsonPropertyName("scannerId")]
        public string? ScannerId { get; set; }
        [JsonPropertyName("genId")]
        public string? GenId { get; set; }
    }
}