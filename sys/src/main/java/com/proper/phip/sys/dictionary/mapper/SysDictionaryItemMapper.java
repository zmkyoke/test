package com.proper.phip.sys.dictionary.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.dictionary.entity.SysDictionaryItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据字典项目Mapper
 */
@Mapper
public interface SysDictionaryItemMapper extends BaseMapper<SysDictionaryItemEntity> {

    /**
     * 取得数据字典项目列表
     * @param typeId 类别ID
     * @param itemCode 项目编码
     * @param status 状态
     * @return 字典项目列表
     */
    @Select("<script>SELECT * FROM PHIP_SYS_DICTIONARY_ITEM WHERE 1=1"
        + "<if test='typeId!=null'> and type_id = #{typeId}</if>"
        + "<if test='typeCode!=null'> and type_code = #{typeCode}</if>"
        + "<if test='itemCode!=null'> and item_code = #{itemCode}</if>"
        + "<if test='status!=null'> and status = #{status}</if>"
        + " ORDER BY seq_num</script>")
    List<SysDictionaryItemEntity> getDictionaryItems(@Param("typeId") String typeId, @Param("typeCode") String typeCode,
                                                     @Param("itemCode") String itemCode, @Param("status") String status);
}
