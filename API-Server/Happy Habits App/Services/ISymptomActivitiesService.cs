using Happy_Habits_App.Forms;

namespace Happy_Habits_App.Services
{
    public interface ISymptomActivitiesService
    {
        public Task AddSymptomActivity(SymptomForm form);
        public Task<List<string>> GetTopSymptoms(string userId, int month, int year);
    }
}
