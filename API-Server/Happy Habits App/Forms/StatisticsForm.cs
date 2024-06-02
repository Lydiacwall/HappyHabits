using Newtonsoft.Json;
using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class StatisticsForm
    {
        [JsonPropertyName("senderId")]
        public string SenderId { get; set; }
        [JsonPropertyName("groupId")]
        public string GroupId { get; set; }
        [JsonPropertyName("statistics")]
        public Dictionary<string, object> Statistics { get; set; }
        [JsonPropertyName("type")]
        public string Type { get; set; }
        [JsonPropertyName("friendUsername")]
        public string friendUsername { get; set; } = "Panos";
        // TODO Test
        [JsonPropertyName("email")]
        public string Email { get; set; } = "tsolkasmiltos@gmail.com";
    }
}
