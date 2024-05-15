using MongoDB.Bson.Serialization.Attributes;
using System.Linq.Expressions;

namespace Happy_Habits_App.Model
{
    public class MedicHabit(DateOnly date, string userId) : Habit(date, userId)
    {
        [BsonElement("medicines")]
        public Dictionary<string, Tuple<int, bool>> Medicines { get; set; } = new Dictionary<string, Tuple<int, bool>>();
    }
}
