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

        public async Task<List<string>> GetTopSymptoms(string userId, int month, int year)
        {
            List<Symptom> symptoms = await _symptomActivitiesRepository.GetSymptomsByMonthAndUserAsync(year, month, userId);

            Dictionary<string, int> types = new Dictionary<string, int>();
            foreach (var symptom in symptoms)
            {
                if (types.ContainsKey(symptom.Type))
                {
                    types[symptom.Type]++;
                }
                else
                {
                    types[symptom.Type] = 0;
                }
            }

            types = types.OrderByDescending(pair => pair.Value).Take(5).ToDictionary(pair => pair.Key, pair => pair.Value);

            List<string> topSymptoms = new List<string>();

            foreach (var kvp in types)
            {
                topSymptoms.Add(kvp.Key);
            }

            return topSymptoms;
        }
    }
}
