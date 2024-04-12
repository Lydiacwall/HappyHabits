using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IUserService
    {
        public Task<User> GetByPasswordAndEmailAsync(string? password, string? email);
    }
}
