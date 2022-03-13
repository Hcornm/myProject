package com.example.reply_api.model;

import com.example.reply_api.domain.Members;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembersDto {

    @Getter
    @Setter
    public static class membersList {

        private String username;

        public membersList(Members members) {
            this.username = members.getUsername();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class membersInfo {
        private Long membersId;
        private String username;
        private Boolean isLogin;

        public membersInfo(Members members) {
            this.membersId = members.getMembersId();
            this.username = members.getUsername();
            this.isLogin = true;
        }
    }

    @Getter
    @Setter
    public static class membersInfoParam {
        private String username;
        private String password;

    }
}
