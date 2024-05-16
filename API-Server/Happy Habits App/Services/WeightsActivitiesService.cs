using Happy_Habits_App.Forms;
using Happy_Habits_App.Repositories;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public class WeightsActivitiesService : IWeightsActivitiesService
    {
        private readonly IWeightsActivitiesRepository _weightsActivitiesRepository;
        public WeightsActivitiesService(IWeightsActivitiesRepository weightsActivitiesRepository) 
        {
            _weightsActivitiesRepository = weightsActivitiesRepository;
        }

        public async Task AddWeightsActivity(WeightsForm form)
        {
            await _weightsActivitiesRepository.CreateWeightsActivity(
                new Weights(
                    DateOnly.Parse(form.Date),
                    form.UserId,
                    form.Type,
                    form.Time,
                    form.Duration,
                    form.Notes,
                    "kg",
                    form.Exercises
                    ));
        }
    }
}
