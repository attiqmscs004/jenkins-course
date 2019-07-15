job('NodeJS Docker example') {
    scm {
        git('https://github.com/attiqmscs004/jenkins-course') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User - Attiq')
            node / gitConfigEmail('jenkins-dsl@newtech.academy-ufone')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('attiqmscs004/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
