## Git Cheatsheet

This is a quick cheatsheet for those that are new to git in the command line. Each bullet point below explains a different command in git.

- **Clone a repository**
   - ```git clone https://github.com/<user_name>/CISC181-Sudoku.git``` : This command will clone a remote git repository locally in the current directory
- **Help**
   - ```git remote add origin https://github.com/jvillemare/CISC181-Sudoku``` : This will add a remote repository to the local git repository, allowing you to pull/push to a different remote
- **Rebase your Local Repository**
   - ```git pull --rebase origin master``` : This will update your local repository with the origin's master branch. If you want to update your local repository from the official repository, run ```git pull --rebase origin master```. This will pull the master branch from the ```origin``` remote repository. These commands should be run after every time you commit, and before you make changes to your local code
- **Track (add) your changes**
   - ```git add --all``` : This command will make all changes in a local git repository tracked. This command must be executed before you attempt to commit your code
- **Commit your changes**
  - ```git commit -m "MESSAGE"``` : This command will commit your code with the commit message "MESSAGE". Please change the message to match what changes you made in the code during this commit
- **Send your changes to GitHub**
  - ```git push``` : This command will push your local changes to the ```origin``` remote repository
