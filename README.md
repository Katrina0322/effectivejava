# effectivejava版本控制规则

## 分支管理

* 开始新版本迭代：根据迭代计划从`master`分支上创建新的开发分支进行
* 开始新功能开发：在开发分支上创建新功能分支进行
* 完成新功能开发：将功能分支基于开发分支进行`rebase`，同时删除跟此功能远程分支，重新push远程分支做code review，通过后合并到开发分支
* 完成新版本迭代：根据开发里程碑将开发分支通过`git rebase`并合并到`master`同时删除开发分支
* BUG修复：在`master`创建新分支进行修改，之后合并回`master`并根据需要合并到开发分支和历史版本

## 分支命名

* 开发分支应标明版本号
* 功能分支格式`feature/{JIRA号}_{负责人姓名}`
* bug修复分支格式`hotfix/{JIRA号}_{负责人姓名}`
* 因重构或改进的工作分支格式`improve/{JIRA号}_{负责人姓名}`

## push / pull

* 更新代码时尽量使用 `git pull --rebase` 保持在fast-forward状态下解决冲突
* 已经进行完`rebase`的本地分支，应立即将对应的远程分支删除`git push origin :feature/JIRA`

## commit message

* 合并到开发分支的feature分支必须以JIRA号开头
* 开发分支`rebase`到`master`时的message要写CHANGELOG
* `master`合并开发分支或BUG修改分支时应使用`git merge --no-ff`

*****
## 工作流过程实例

### 开始新版本迭代
```
git checkout master  #check到master最新版
git checkout -b Ai_4.1.0
git push -u origin Ai_4.1.0
```
### 开始新功能开发
```
git checkout Ai_4.1.0 #check到开发分支最新版
git checkout -b feature/AI-111_guest
```
### 提交
```
git commit -m '修改某文件' # 这里JIRA号不是必须的，以后有机会改
```
### 多人协作
```
git pull --rebase
```
### 需从主功能分支合并代码
```
git rebase -i origin/Ai_4.1.0
```
**切勿使用git merge来合并甚至处理代码冲突**

### 完成新功能开发
```
git checkout feature/AI-111_guest
git rebase -i Ai_4.1.0     #rebase时合并所有提交并在合并后的提交的commit message中写上JIRA号
git push origin :feature/AI-111_guest #删除功能分支的远程分支
git push origin feature/AI-111_guest #push rebase之后的功能分支做code review
# review通过后
git checkout Ai_4.1.0 #check出最新开发分支
git merge feature/AI-111_guest #合并rebase后的开发分支同时
git push origin Ai_4.1.0
```
### 完成新版本迭代
```
git checkout Ai_4.1.0
git rebase -i master #可根据情况判断是否需要合并commit，rebase之后的commit message会包含所有的JIRA号，可以很轻松的完成CHANGELOG
git checkout master
git merge --no-ff Ai_4.1.0 && git push origin :Ai_4.1.0 # 合并rebase后的开发分支并删掉远程分支
git tag -a v4.1.0 #使用工具或git打上版本号
git push origin master --follow-tags
```
### BUG修复及改进
```
git checkout master
git checkout -b hotfix/AI-111_guest
# bug修复及测试
git rebase -i master #用rebase合并所有提交并做code review
git checkout master
git merge hotfix/AI-111_guest
git checkout Ai_4.1.0 && git merge hotfix/AI-111_guest
```
**历史版本可通过cherry-pick应用bug修复**

**rebase总是在code review之前进行**

**code review如果不通过可直接在rebase的分支上做开发，重新rebase并提交code review**

*****
