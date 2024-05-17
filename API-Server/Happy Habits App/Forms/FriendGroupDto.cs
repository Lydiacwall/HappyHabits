namespace Happy_Habits_App.Forms
{
    public class FriendGroupDto(string friendUsername, string groupId)
    {
        public string FriendUsername { get; set; } = friendUsername;
        public string GroupId { get; set; } = groupId;
    }
}
