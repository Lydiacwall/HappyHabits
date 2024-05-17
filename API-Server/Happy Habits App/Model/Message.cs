namespace Happy_Habits_App.Model
{
    public class Message(string senderId, DateTime timestamp, string content)
    {
        public string SenderId { get; set; } = senderId; 
        public DateTime Timestamp { get; set; } = timestamp; 
        public string Content { get; set; } = content;
    }
}
