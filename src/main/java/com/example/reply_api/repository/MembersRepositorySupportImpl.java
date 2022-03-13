package com.example.reply_api.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.example.reply_api.domain.Members;
import com.example.reply_api.domain.QMembers;
import com.example.reply_api.model.MembersDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MembersRepositorySupportImpl extends QuerydslRepositorySupport implements MembersRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public MembersRepositorySupportImpl(EntityManager entityManager) {
        super(Members.class);
        super.setEntityManager(entityManager);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<MembersDto.membersList> membersList() {

        final QMembers members = QMembers.members;
        final BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(members.delYn.eq('N'));
        booleanBuilder.and(members.useYn.eq('Y'));

        return jpaQueryFactory.select(Projections.constructor(MembersDto.membersList.class, members))
                .from(members)
                .where(booleanBuilder)
                .fetch();
    }

    @Override
    public Optional<Members> findByIdAndPw(MembersDto.membersInfoParam membersInfoParam) {
        final QMembers members = QMembers.members;
        final String userName = membersInfoParam.getUsername();
        final String passWord = membersInfoParam.getPassword();
        final BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(members.delYn.eq('N'));
        booleanBuilder.and(members.useYn.eq('Y'));
        booleanBuilder.and(members.username.eq(userName));
        booleanBuilder.and(members.password.eq(passWord));

        return Optional.ofNullable(jpaQueryFactory.select(members)
                .from(members)
                .where(booleanBuilder)
                .fetchFirst());
    }

}
