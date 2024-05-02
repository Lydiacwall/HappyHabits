using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class FastActivity(string date, string userId, string type, string time, string notes, string unitMeasurement, float? quantity, float elevation) : Workout(date, userId, type, time, notes, unitMeasurement, quantity)
    {
        [BsonElement("elevation")]
        public float Elevation { get; set; } = elevation;
    }
}
