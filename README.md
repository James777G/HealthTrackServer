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


### Interfaces
- Interfaces or abstract classes of the invocation layer must be documented
- 所用调用层的接口一定要有注释
- Service interfaces must end with xxxxService, e.g. `ItemService`
- Service层的接口必须以 xxxxService 结尾，例如 `ItemService`
## System Architecture
<img src="https://github.com/James777G/HealthTrackServer/assets/110001509/962130ea-ca0a-4c0a-baac-6bba08556fa9" width="460" height="360">
