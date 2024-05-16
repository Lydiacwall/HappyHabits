using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

namespace Happy_Habits_App.Services
{
    public class FastActivitiesService : IFastActivitiesService
    {
        private readonly IFastActivitiesRepository _fastActivitiesRepository;
        public FastActivitiesService(IFastActivitiesRepository fastActivitiesRepository)
        {
            _fastActivitiesRepository = fastActivitiesRepository;
        }

        public async Task AddFastActivity(FastActivityForm form)
        {
            await _fastActivitiesRepository.CreateFastActivityAsync(
                new FastActivity(
                    DateOnly.Parse(form.Date),
                    form.UserId,
                    form.Type,
                    form.Time,
                    form.Duration,
                    form.Notes,
                    "km",
                    form.Quantity,
                    form.Elevation
                    ));
        }
    }
}
