package io.litmuschaos.response;

import java.util.List;
import java.util.StringJoiner;

public class ProjectsStatusResponse {

    public static class Member {

        private String userID;
        private String username;
        private String invitation;
        private long joinedAt;

        public String getUserID() {
            return userID;
        }

        public String getUsername() {
            return username;
        }

        public String getInvitation() {
            return invitation;
        }

        public long getJoinedAt() {
            return joinedAt;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Member.class.getSimpleName() + "[", "]")
                    .add("userID='" + userID + "'")
                    .add("username='" + username + "'")
                    .add("invitation='" + invitation + "'")
                    .add("joinedAt=" + joinedAt)
                    .toString();
        }
    }

    public static class Members {

        private List<Member> owner;
        private int total;

        public List<Member> getOwner() {
            return owner;
        }

        public int getTotal() {
            return total;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Members.class.getSimpleName() + "[", "]")
                    .add("owner=" + owner)
                    .add("total=" + total)
                    .toString();
        }
    }

    public static class Project {

        private String name;
        private String projectID;
        private Members members;

        public String getName() {
            return name;
        }

        public String getProjectID() {
            return projectID;
        }

        public Members getMembers() {
            return members;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Project.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("projectID='" + projectID + "'")
                    .add("members=" + members)
                    .toString();
        }
    }

    private List<Project> data;

    public List<Project> getData() {
        return data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProjectsStatusResponse.class.getSimpleName() + "[", "]")
                .add("data=" + data)
                .toString();
    }
}