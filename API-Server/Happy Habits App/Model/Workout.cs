using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public abstract class Workout(string date, string userId, string type, string time, string notes, string unitMeasurement, float? quantity) : Habit(date, userId)
    {
        [BsonElement("type")]
        public string Type { get; set; } = type;
        [BsonElement("time")]
        public string Time { get; set; } = time;
        [BsonElement("notes")]
        public string Notes { get; set; } = notes;
        [BsonElement("unitMeasurement")]
        public string UnitMeasurement { get; set; } = unitMeasurement;
        [BsonElement("quantity")]
        public float? Quantity { get; set; } = quantity;
    }
}
