package com.bbms.boardmanagement.cli.user.domain;

/**
 * 회원 등급 정책
 * 가입시 기본 등급 BRANCH
 * 게시글 10개 작성시 TREE
 * 게시글 20개 작성시 FOREST
 * 게시글 30개 작성시 EARTH
 */
public class RankPolicy {

    public static final int EARTH_REQUIREMENT = 30;
    public static final int FOREST_REQUIREMENT = 20;
    public static final int TREE_REQUIREMENT = 10;

    //등급 조정 기능
    public static void changeRank(User user) {

        int totalPosting = user.getPostedPostCount();
        int totalCommenting = user.getPostedCommentCount();
        int totalEnrollment = totalPosting + (totalCommenting / 5);//댓글 5개를 게시글 1개로 취급

        if (totalEnrollment >= EARTH_REQUIREMENT) {
            user.setUserRank(Rank.EARTH);
        } else if (totalEnrollment >= FOREST_REQUIREMENT) {
            user.setUserRank(Rank.FOREST);
        } else if (totalEnrollment >= TREE_REQUIREMENT) {
            user.setUserRank(Rank.TREE);
        } else {
            return;
        }
    }
}
