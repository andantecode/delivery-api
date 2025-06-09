package org.delivery.db.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

@SuperBuilder // 부모로부터 상속받은 필드까지 builder 패턴으로 활용
@Data
@EqualsAndHashCode(callSuper = true) // 객체 비교할 때 사용 (부모에 있는 값까지 포함해서 비교)
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

}
