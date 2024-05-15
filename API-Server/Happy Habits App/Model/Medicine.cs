using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class Medicine(string? userId, string name, float? dosageQuantity, string? dosageUnitMeasurement, DateOnly startDay, DateOnly endDay, int timesShouldBeTaken)
    {
        [BsonId]
        [BsonRepresentation(MongoDB.Bson.BsonType.ObjectId)]
        public string? Id { get; set; }
        [BsonElement("userId")]
        public string? UserId { get; set; } = userId;
        [BsonElement("name")]
        public string Name { get; set; } = name;
        [BsonElement("dosageQuantity")]
        public float? DosageQuantity { get; set; } = dosageQuantity;
        [BsonElement("dosageUnitMeasurement")]
        public string? DosageUnitMeasurement { get; set; } = dosageUnitMeasurement;
        [BsonElement("startDay")]
        public DateOnly StartDay { get; set; } = startDay;
        [BsonElement("endDay")]
        public DateOnly EndDay { get; set; } = endDay;
        [BsonElement("timesShouldBeTaken")]
        public int TimesShouldBeTaken { get; set; } = timesShouldBeTaken;
        [BsonElement("active")]
        public bool Active { get; set; } = true;
    }
}
