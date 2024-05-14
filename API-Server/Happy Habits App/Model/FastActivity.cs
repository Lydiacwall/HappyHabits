using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class FastActivity(DateOnly date, string userId, string type, string time, string duration, string notes, string unitMeasurement = "km", float quantity=0, float elevation=0) : Workout(date, userId, type, time, duration, notes, unitMeasurement)
    {
        [BsonElement("quantity")]
        public float Quantity { get; set; } = quantity;
        [BsonElement("elevation")]
        public float Elevation { get; set; } = elevation;
    }
}
