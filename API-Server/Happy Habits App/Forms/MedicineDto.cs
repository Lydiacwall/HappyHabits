using Happy_Habits_App.Model;
using MongoDB.Bson.Serialization.Attributes;
using System.Xml.Linq;

namespace Happy_Habits_App.Forms
{
    public class MedicineDto(string? id, string? userId, string name, float? dosageQuantity, string? dosageUnitMeasurement, string? startDay, string? endDay, int timesShouldBeTaken, int timesTaken, string? notes)
    {
        public string? Id { get; set; } = id;
        public string? UserId { get; set; } = userId;
        public string Name { get; set; } = name;
        public float? DosageQuantity { get; set; } = dosageQuantity;
        public string? DosageUnitMeasurement { get; set; } = dosageUnitMeasurement;
        public string? StartDay { get; set; } = startDay;
        public string? EndDay { get; set; } = endDay;
        public int TimesShouldBeTaken { get; set; } = timesShouldBeTaken;
        public int TimesTaken { get; set; } = timesTaken;
        public string? Notes { get; set; } = notes;
    }
}