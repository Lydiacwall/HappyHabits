using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IUserRepository
    {
        Task<List<User>> GetAllUsersAsync();
        Task<User> GetUserByPasswordAndEmailAsync(string? password, string? email);
        Task<User> GetUserByEmail(string? email);
        Task CreateUserAsync(User user);
        Task UpdateUserAsync(User user);
        Task<User> FindUserByIdAsync(string id);

        Task<string> GetUsernameById(string id);
        Task<int> GetSleepGoalById(string id);
    }
}
