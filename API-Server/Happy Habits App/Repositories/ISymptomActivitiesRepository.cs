using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface ISymptomActivitiesRepository
    {
        Task CreateSymptomActivityAsync(Symptom symptom);
    }
}
