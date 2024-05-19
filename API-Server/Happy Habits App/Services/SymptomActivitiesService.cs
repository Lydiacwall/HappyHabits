using Happy_Habits_App.Forms;
using Happy_Habits_App.Repositories;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public class SymptomActivitiesService : ISymptomActivitiesService
    {
        private readonly ISymptomActivitiesRepository _symptomActivitiesRepository;
        public SymptomActivitiesService(ISymptomActivitiesRepository symptomActivitiesRepository) => _symptomActivitiesRepository = symptomActivitiesRepository;
        public async Task AddSymptomActivity(SymptomForm form)
        {
            await _symptomActivitiesRepository.CreateSymptomActivityAsync(
                new Symptom(
                    DateOnly.Parse(form.Date),
                    form.UserId,
                    form.Name,
                    form.Notes));
        }
    }
}
