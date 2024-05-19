using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Forms
{
    public class SymptomForm : HabitForm 
    {
        [BsonElement("name")]
        public string? Name { get; set; } = null;
        [BsonElement("notes")]
        public string? Notes { get; set; } = null;
        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !string.IsNullOrEmpty(Date) &&
                       !string.IsNullOrEmpty(Name) &&
                       !string.IsNullOrEmpty(Notes);
            }
        }
    }
}
