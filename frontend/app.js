// Import Package
const express = require('express');
const bodyParser = require('body-parser');
const exphbs = require('express-handlebars');
const path = require('path');
const nodemailer = require('nodemailer');

const config = require('./config');

// Set Package
const app = express();

app.engine('handlebars', exphbs());
app.set('view engine', 'handlebars');

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

// Server Start Notification
app.listen(3000, () => console.log("Server Started on port 3000..."));

// Set Static Folder Path
app.use('/public', express.static(path.join(__dirname, 'public')));

// Get Index Page Request
app.get ('/', (req, res) => {
    res.render(config.theme);
});

// Post Email Request
app.post('/send', (req, response) => {
    // Alert if failed to sending email
    const failAlert = `
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                Failed to create incident.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                </button>
        </div>
    `;

    const axios = require('axios').default;

    console.log("req.body.name ", req.body.name)

    axios
        .post(config.serverUrl + 'incident-request/save', {
                name: req.body.name,
            organization: req.body.organization,
            application: req.body.application,
            url: req.body.url,
            api: req.body.api,
            errorCode: req.body.errorCode,
            email: req.body.email,
            message: req.body.message
        })
        .then(res => {
            // Alert if successfully sending form
            console.log(`statusCode: ${res.statusCode}`)
            console.log(res)

            const successAlert = `
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                        Incident <b> ${res.data.incidentNumber} </b> has been successfully created 
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                        </button>
                </div>
            `;
            response.render(config.theme, {msg: successAlert});
        })
        .catch(error => {
            console.error(error)
            response.render(config.theme, {msg: failAlert});
        })
});
