using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

namespace Happy_Habits_App.Services
{
    public class ToiletActivitiesService : IToiletActivitiesService
    {
        private readonly IToiletActivitiesRepository _toiletActivitiesRepository;
        public ToiletActivitiesService(IToiletActivitiesRepository toiletActivitiesRepository) => _toiletActivitiesRepository = toiletActivitiesRepository;
        public async Task AddToiletActivity(ToiletForm form)
        {
            await _toiletActivitiesRepository.CreateToiletActivityAsync(
                    new Toilet(
                        DateOnly.Parse(form.Date),
                        form.UserId,
                        form.Type,
                        form.Hour,
                        form.Note)
                    );
        }

        public Task<List<Toilet>> GetAllToiletActivities()
        {
            throw new NotImplementedException();
        }
    }
}
