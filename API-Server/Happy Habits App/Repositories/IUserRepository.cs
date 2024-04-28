using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IUserRepository
    {
        Task<List<User>> GetAllUsersAsync();
        Task<User> GetUserByPasswordAndEmailAsync(string? password, string? email);
        Task<User> GetUserByEmail(string? email);
        Task CreateUserAsync(User user);
    }
}
