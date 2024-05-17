using MongoDB.Driver.Core.Authentication;

namespace Happy_Habits_App.Forms
{
    public class MessageDto(string senderId, string timestamp, string content)
    {
        public string SenderId { get; set; } = senderId;
        public string Timestamp { get; set; } = timestamp;
        public string Content { get; set; } = content;
    }
}
