package camput.Impl;

import camput.Dto.LikeDto;
import camput.Service.LikeService;
import camput.domain.CampLiked;
import camput.domain.Camput;
import camput.domain.Member;
import camput.domain.MemberLiked;
import camput.repository.CampLikedRepository;
import camput.repository.CamputRepository;
import camput.repository.MemberLikedRepository;
import camput.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeImpl implements LikeService {

    private final CampLikedRepository campLikedRepository;
    private final MemberLikedRepository memberLikedRepository;
    private final CamputRepository camputRepository;
    private final MemberRepository memberRepository;

    @Override
    public LikeDto like(String loginId, String campName) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonCampName = (JSONObject)jsonParser.parse(campName);
        String name =(String)jsonCampName.get("campName");

        Camput camp = camputRepository.findByCampName(name);
        log.info("camp={}",camp.getCampName());
        Member member = memberRepository.findByMemberLoginId(loginId);
        MemberLiked byMember = memberLikedRepository.findByMemberAndLikedCampName(member,name);

        LikeDto result;
        if(byMember==null){
            Camput addLike = camp.addLike();
            makeLike(camp, member, addLike);
            result = makeLikeDto(addLike, 1);
        }else{
            CampLiked campLiked = campLikedRepository.findByMemberLikedAndCamput(byMember,camp);
            Camput minusLike = camp.minusLike();
            campLikedRepository.deleteById(campLiked.getId());
            memberLikedRepository.deleteById(byMember.getId());
            result = makeLikeDto(minusLike, 0);
        }
        return result;
    }
    private void makeLike(Camput camp, Member member, Camput addLike) {

        MemberLiked memberLike = MemberLiked.builder()
                .member(member)
                .likedCampAddress(camp.getCampAddress().getSimpleAddr())
                .likedCampName(camp.getCampName())
                .likedCampImageUrl(camp.getCampImageFilesList().get(0).getCampOriginalUrl())
                .build();
        MemberLiked mLike = memberLikedRepository.save(memberLike);
        CampLiked campLike = CampLiked.builder()
                .memberLiked(mLike)
                .camput(addLike)
                .build();
        CampLiked cLike = campLikedRepository.save(campLike);

    }

    private static LikeDto makeLikeDto(Camput minusLike, int like) {
        return LikeDto.builder()
                .totalLike(minusLike.getMemberLikeTotalCount())
                .like(like)
                .build();
    }
}
