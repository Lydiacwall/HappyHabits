using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IMessageRepository
    {
        Task<Queue<Message>> GetAllMessagesByGroupId(string groupId);

        Task UpdateFriendGroupChat(FriendGroup group);

        Task<FriendGroup> GetFriendGroupById(string groupId);

        Task<List<FriendGroup>> GetAllFriends(string userId);
    }
}
