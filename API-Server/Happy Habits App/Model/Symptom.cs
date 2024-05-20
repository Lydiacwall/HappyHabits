using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class Symptom(DateOnly date, string userId, string name, string notes) : Habit(date, userId)
    {
        [BsonElement("Type")]
        public string Type { get; set; } = name;
        [BsonElement("Note")]
        public string Note { get; set; } = notes;
    }
}
