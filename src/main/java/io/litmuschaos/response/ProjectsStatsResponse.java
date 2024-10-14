package io.litmuschaos.response;

import java.util.List;

public class ProjectsStatsResponse {
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
        return "ProjectsStatusResponse{" +
                "name='" + name + '\'' +
                ", projectID='" + projectID + '\'' +
                ", members=" + members +
                '}';
    }

    public static class Members {
        private List<Owner> owner;
        private int total;

        public List<Owner> getOwner() {
            return owner;
        }

        public int getTotal() {
            return total;
        }


        @Override
        public String toString() {
            return "Members{" +
                    "owner=" + owner +
                    ", total=" + total +
                    '}';
        }
    }

    public static class Owner {
        private String userID;
        private String username;
        private String invitation;
        private Long joinedAt;
        private Long deactivatedAt;

        public String getUserID() {
            return userID;
        }

        public String getUsername() {
            return username;
        }

        public String getInvitation() {
            return invitation;
        }

        public Long getJoinedAt() {
            return joinedAt;
        }

        public Long getDeactivatedAt() {
            return deactivatedAt;
        }

        @Override
        public String toString() {
            return "Owner{" +
                    "userID='" + userID + '\'' +
                    ", username='" + username + '\'' +
                    ", invitation='" + invitation + '\'' +
                    ", joinedAt=" + joinedAt +
                    ", deactivatedAt=" + deactivatedAt +
                    '}';
        }
    }
}
