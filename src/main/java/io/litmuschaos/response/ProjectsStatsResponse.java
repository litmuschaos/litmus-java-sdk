package io.litmuschaos.response;

import java.util.List;

public class ProjectsStatsResponse {

    private String name;
    private String projectID;
    private Members members;

    public ProjectsStatsResponse(String name, String projectID, Members members) {
        this.name = name;
        this.projectID = projectID;
        this.members = members;
    }

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
        private Integer total;

        public Members(List<Owner> owner, Integer total) {
            this.owner = owner;
            this.total = total;
        }

        public List<Owner> getOwner() {
            return owner;
        }

        public Integer getTotal() {
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

        public Owner(String userID, String username, String invitation, Long joinedAt, Long deactivatedAt) {
            this.userID = userID;
            this.username = username;
            this.invitation = invitation;
            this.joinedAt = joinedAt;
            this.deactivatedAt = deactivatedAt;
        }

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
