package com.proper.phip.sys.dictionary.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.dictionary.entity.SysDictionaryTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据字典类别Mapper
 */
@Mapper
public interface SysDictionaryTypeMapper extends BaseMapper<SysDictionaryTypeEntity> {

    /**
     * 取得数据字典类别列表
     * @param typeCode 类别编码
     * @return 数据字典类别列表
     */
    @Select("<script>SELECT * FROM PHIP_SYS_DICTIONARY_TYPE WHERE 1=1"
        + "<if test='typeCode!=null'> and type_code = #{typeCode}</if>"
        + " ORDER BY TYPE_CODE</script>")
    List<SysDictionaryTypeEntity> getDictionaryTypes(@Param("typeCode") String typeCode);
}
