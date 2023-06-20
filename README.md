# HealthTrackServer
Backend Java Server Powered by Springboot，the Health-track calculator utilizes user-provided information, including age, lifestyle, and medical conditions, to effectively analyze relevant databases. This analysis yields real-life disease data tailored to the user's specific information. Furthermore, the Health-track calculator offers personalized advice on adopting healthy lifestyles. Additionally, it provides comprehensive information on specific medications, including dosage instructions, potential side effects, and necessary precautions to ensure user safety.

## Coding Guideline
### General
- The programming of the core functionalities should be based on the abstract classes or interfaces
- 编程应基于抽象类或接口进行编程
- Using exceptions to replace conditional statements is acceptable in this project, but maintaining the exception chain is a must
- 本项目可以使用异常来替换条件判断，但是必须维护异常链
- The attributes of plain old Java objects should not be primitive
- 所有的 POJO 类都应使用包装数据类型
- Avoid the use of `!` in conditional statements
- 尽量避免在条件判断里使用 `!`


### Interfaces
- Interfaces or abstract classes of the invocation layer must be documented
- 所用调用层的接口一定要有注释
- Service interfaces must end with xxxxService, e.g. `ItemService`
- Service层的接口必须以 xxxxService 结尾，例如 `ItemService`
- The Implementation of the service interfaces must end with xxxxServiceProvider
- Service层的接口的实现必须命名为 xxxxServiceProvider, 例如 `ItemServiceProvider`
- If the interface describes a capability, then it must end with -able, e.g. `Cacheable`
- 若接口描述能力，则用 -able 结尾

### Classes
- Attributes describing states, e.g. `isSuccess` must be renamed to 'success'
- 用来描述状态的成员变量不能是 isXXXX
- Intentionally used design patterns should be explicitly mentioned, e.g. `ItemFactory`
- 使用设计模式需要在类名中提及以便他人理解

### Naming Consistency
- In DAO/Service interfaces, if a single object is expected to be returned, then use getXXXX, if multiple objects, then use listXXXX, e.g. `getItem`, `listItems`
- 在 DAO 或 Service 层接口里，若返回单一对象，使用Get， 返回多对象使用 list, 其他的名字，比如 fetch, retrieve 都不应被使用
- Count for getting the number(aggregate functions), insert for insertion of data, delete for deletion of data, update for updation e.g. `deleteObject`, `countObjects`
- Count来获取统计值， insert 来插入数据, delete 删除数据, update 更新数据， 其他例如 remove, save 等等都不应被使用

### Features
- Use warning annotation if unsure about code.
``enum Type{DEBUG, REVIEW_NEEDED, ADDITIONAL_FEATURE, DELETE_IN_FUTURE}``java
## System Architecture
<img src="https://github.com/James777G/HealthTrackServer/assets/110001509/962130ea-ca0a-4c0a-baac-6bba08556fa9" width="460" height="360">
