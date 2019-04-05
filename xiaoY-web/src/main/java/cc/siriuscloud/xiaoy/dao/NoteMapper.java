package cc.siriuscloud.xiaoy.dao;

import cc.siriuscloud.xiaoy.domain.Note;

public interface NoteMapper {
    int deleteByPrimaryKey(Integer noteId);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer noteId);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKeyWithBLOBs(Note record);

    int updateByPrimaryKey(Note record);
}