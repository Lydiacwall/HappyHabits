using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

namespace Happy_Habits_App.Services
{
    public class MessageService : IMessageService
    {
        public readonly IMessageRepository _messageRepository;

        public readonly IUserRepository _userRepository;

        public MessageService(IMessageRepository messageRepository, IUserRepository userRepository)
        {
            _messageRepository = messageRepository;
            _userRepository = userRepository;
        }

        public async Task<List<MessageDto>> GetMessagesOfGroupChat(string groupId)
        {
            // Retrieve the friend group messages
            List<Message> messages = new List<Message>(await _messageRepository.GetAllMessagesByGroupId(groupId));

            if (messages == null)
            {
                return new List<MessageDto>();
            }

            List<MessageDto> conversation = new List<MessageDto>();
            // Convert the Message to MessageDto
            foreach(var message in messages)
            {
                // Find username of sender Id
                MessageDto messageDto = new MessageDto(
                    message.SenderId,
                    message.Timestamp.ToString(),
                    message.Content);
                conversation.Add(messageDto);
            }

            return conversation;
        }

        public async Task StoreMessage(string message, string senderId, string groupId, DateTime today)
        {
            FriendGroup group = await _messageRepository.GetFriendGroupById(groupId);

            if (group !=  null)
            {
                Message newMessage = new Message(senderId, today, message);
                group.Messages.Enqueue(newMessage);
                await _messageRepository.UpdateFriendGroupChat(group);
            }
        }

        public async Task<List<FriendGroupDto>> GetAllFriendGroups(string userId)
        {
            // Search for all groups and get the usernames
            List<FriendGroup> friendGroups = await _messageRepository.GetAllFriends(userId);
            List<FriendGroupDto> friendGroupDtos = new List<FriendGroupDto>();

            // Find the friend names and groupIds
            foreach (var group in friendGroups) {
                string friendId;
                if (group.Group.Item1 == userId)
                {
                    friendId = group.Group.Item2;
                }
                else
                {
                    friendId = group.Group.Item1;
                }
                string friendName = await _userRepository.GetUsernameById(friendId);
                FriendGroupDto groupDto = new FriendGroupDto(friendName, group.Id);
                friendGroupDtos.Add(groupDto);
            }

            return friendGroupDtos.OrderBy(fg => fg.FriendUsername).ToList();
        }
    }
}
